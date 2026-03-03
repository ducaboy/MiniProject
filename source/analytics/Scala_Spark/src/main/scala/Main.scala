import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._


object Main {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("SmartClinic Analytics")
      .master("local[*]") // Runs locally using all CPU cores
      .getOrCreate()

    println("🔥 Spark session started successfully!")

    val jdbcUrl = "jdbc:postgresql://localhost:5432/smartclinic"

    val df: DataFrame = spark.read
      .format("jdbc")
      .option("url", jdbcUrl)
      .option("dbtable", "appointments")  // table name
      .option("user", "postgres")         // change if needed
      .option("password", "postgres")     // change if needed
      .option("driver", "org.postgresql.Driver")
      .load()

    println("📊 Appointments loaded:")
    df.show()

    println("📅 Appointments per Day:")

    val perDay = df
      .groupBy(to_date(col("date")).alias("day"))
      .count()
      .orderBy("day")

    perDay.show()

    println("📈 Status Distribution:")

    val statusStats = df
      .groupBy("status")
      .count()

    statusStats.show()

    println(s"📦 Total rows: ${df.count()}")


    spark.stop()
  }
}