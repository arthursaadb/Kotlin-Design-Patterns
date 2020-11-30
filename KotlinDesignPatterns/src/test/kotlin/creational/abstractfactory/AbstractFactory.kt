package creational.abstractfactory

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

interface DataSource

class DatabaseDataSource : DataSource
class NetworkDataSource : DataSource

abstract class DataSourceFactory {
    abstract fun makeDataSource(): DataSource

    companion object {
        inline fun <reified T : DataSource> createFactory(): DataSourceFactory =
            when (T::class) {
                DatabaseDataSource::class -> DatabaseFactory()
                NetworkDataSource::class -> NetworkFactory()
                else -> throw IllegalArgumentException()
            }
    }
}

class DatabaseFactory : DataSourceFactory() {
    override fun makeDataSource(): DataSource = DatabaseDataSource()
}

class NetworkFactory : DataSourceFactory() {
    override fun makeDataSource(): DataSource = NetworkDataSource()
}

class AbstractFactoryTest {

    @Test
    fun `test data source factory`() {
        val dataSourceDatabaseFactory = DataSourceFactory.createFactory<DatabaseDataSource>()
        val dataSource = dataSourceDatabaseFactory.makeDataSource()

        Assertions.assertThat(dataSource).isInstanceOf(DatabaseDataSource::class.java)
    }
}

