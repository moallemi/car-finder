package me.moallemi.carfinder.local.datasource

import io.reactivex.Observable
import me.moallemi.carfinder.data.datasource.CarTypeLocalDataSource
import me.moallemi.carfinder.domain.model.Manufacturer
import me.moallemi.carfinder.local.db.AppDatabase
import me.moallemi.carfinder.local.entity.toLocalManufacturer
import me.moallemi.carfinder.local.entity.toManufacturer
import javax.inject.Inject

class CarTypeLocalDataSourceImpl @Inject constructor(private val db: AppDatabase) : CarTypeLocalDataSource {

    override fun getManufacturers(): Observable<List<Manufacturer>> {
        return db.manufacturerDao().all().map { localItems ->
            localItems.map { localManufacturer ->
                localManufacturer.toManufacturer()
            }
        }
    }

    override fun updateManufacturers(items: List<Manufacturer>) {
        db.manufacturerDao().updateAll(
            *items.map { manufacturer ->
                manufacturer.toLocalManufacturer()
            }.toTypedArray()
        )
    }
}