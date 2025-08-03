import kotlinx.serialization.json.Json

object FHFFPrototypeAMin {
    val json = Json {
        prettyPrint = true
        indent = "    "
    }

    data class ServiceConfig(
        val url: String,
        val method: String,
        val auth: AuthConfig? = null
    )

    data class AuthConfig(
        val type: String,
        val key: String
    )

    val services = listOf(
        ServiceConfig(
            url = "https://api.example.com/users",
            method = "GET"
        ),
        ServiceConfig(
            url = "https://api.example.com/orders",
            method = "POST",
            auth = AuthConfig(
                type = "Bearer",
                key = "YOUR_API_KEY"
            )
        )
    )

    fun parseServiceConfig(jsonStr: String): List<ServiceConfig> {
        return json.decodeFromString(List<ServiceConfig>::class, jsonStr)
    }

    fun main() {
        val jsonStr = json.encodeToString(List<ServiceConfig>::class, services)
        println(jsonStr)

        val parsedServices = parseServiceConfig(jsonStr)
        parsedServices.forEach { println(it) }
    }
}

fun main() {
    FHFFPrototypeAMin.main()
}