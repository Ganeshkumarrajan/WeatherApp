import com.google.gson.annotations.SerializedName

data class ForeCastNetwork(
    @SerializedName("city")
    var city: City?= null,
    @SerializedName("cnt")
    var cnt: Int?= null,
    @SerializedName("cod")
    var cod: String?= null,
    @SerializedName("list")
    var list: List<ForeCast>?= null,
    @SerializedName("message")
    var message: Int?= null
)

data class City(
    @SerializedName("coord")
    var coord: Coord,
    @SerializedName("country")
    var country: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("population")
    var population: Int,
    @SerializedName("sunrise")
    var sunrise: Int,
    @SerializedName("sunset")
    var sunset: Int,
    @SerializedName("timezone")
    var timezone: Int
)

data class ForeCast(
    @SerializedName("clouds")
    var clouds: Clouds? = null,
    @SerializedName("dt")
    var dt: Int?= null,
    @SerializedName("dt_txt")
    var dtTxt: String?= null,
    @SerializedName("main")
    var main: Main?= null,
    @SerializedName("pop")
    var pop: Int?= null,
    @SerializedName("sys")
    var sys: Sys?= null,
    @SerializedName("visibility")
    var visibility: Int?= null,
    @SerializedName("weather")
    var weather: List<Weather>?= null,
    @SerializedName("wind")
    var wind: Wind?= null
)

data class Coord(
    @SerializedName("lat")
    var lat: Double,
    @SerializedName("lon")
    var lon: Double
)

data class Clouds(
    @SerializedName("all")
    var all: Int
)

data class Main(
    @SerializedName("feels_like")
    var feelsLike: Double? = null,
    @SerializedName("grnd_level")
    var grndLevel: Int?= null,
    @SerializedName("humidity")
    var humidity: Int?= null,
    @SerializedName("pressure")
    var pressure: Int?= null,
    @SerializedName("sea_level")
    var seaLevel: Int?= null,
    @SerializedName("temp")
    var temp: Double?= null,
    @SerializedName("temp_kf")
    var tempKf: Double?= null,
    @SerializedName("temp_max")
    var tempMax: Double?= null,
    @SerializedName("temp_min")
    var tempMin: Double?= null,
)

data class Sys(
    @SerializedName("pod")
    var pod: String
)

data class Weather(
    @SerializedName("description")
    var description: String?= null,
    @SerializedName("icon")
    var icon: String?= null,
    @SerializedName("id")
    var id: Int?= null,
    @SerializedName("main")
    var main: String?= null
)

data class Wind(
    @SerializedName("deg")
    var deg: Int,
    @SerializedName("gust")
    var gust: Double,
    @SerializedName("speed")
    var speed: Double
)
