package com.github.ronyv89.opendata.railways
import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.elasticsearch.spark._
import org.elasticsearch.spark.sql._
import org.elasticsearch.spark.sql
import java.sql.Date
import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.functions.{col, udf}
import org.apache.spark.sql.functions.{row_number, max, broadcast}
import org.apache.spark.sql.expressions.Window
import com.typesafe.config._

object ReservationTrains {
  def main(args: Array[String]) {
    val conf = ConfigFactory.load()
    val sparkConf = new SparkConf().setAppName("Railway Time Table Import")
    sparkConf.set("es.nodes", conf.getString("app.elasticsearch.nodes"))
    sparkConf.set("es.nodes.wan.only","true")
    sparkConf.set("es.index.auto.create", "true")
    val sc = new SparkContext(sparkConf)
    val spark = SparkSession.builder().config(sparkConf).getOrCreate()
    import spark.sqlContext.implicits._
    val df = spark
      .read
      .format("csv")
      .option("header", true)
      .load(conf.getString("app.data.paths.railyway_reservation_time_table"))
      .toDF
      .saveToEs("open_data_india/railways_reservation_time_table")
  }
}