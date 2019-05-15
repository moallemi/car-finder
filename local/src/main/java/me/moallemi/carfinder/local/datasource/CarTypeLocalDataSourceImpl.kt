package me.moallemi.carfinder.local.datasource

import io.reactivex.Observable
import me.moallemi.carfinder.data.datasource.CarTypeLocalDataSource
import me.moallemi.carfinder.domain.model.Manufacturer
import me.moallemi.carfinder.local.dao.BuiltDateDao
import me.moallemi.carfinder.local.dao.MainTypeDao
import me.moallemi.carfinder.local.dao.ManufacturerDao
import me.moallemi.carfinder.local.entity.LocalBuiltDate
import me.moallemi.carfinder.local.entity.LocalMainType
import me.moallemi.carfinder.local.entity.toLocalManufacturer
import me.moallemi.carfinder.local.entity.toManufacturer
import javax.inject.Inject

class CarTypeLocalDataSourceImpl @Inject constructor(
    private val manufacturerDao: ManufacturerDao,
    private val mainTypeDao: MainTypeDao,
    private val builtDateDao: BuiltDateDao
) : CarTypeLocalDataSource {

    override fun getManufacturers(): Observable<List<Manufacturer>> {
        return manufacturerDao.all()
            .map { localItems ->
                localItems.map { localManufacturer ->
                    localManufacturer.toManufacturer()
                }
            }
    }

    override fun updateManufacturers(items: List<Manufacturer>) {
        manufacturerDao.updateAll(
            *items.map { manufacturer ->
                manufacturer.toLocalManufacturer()
            }.toTypedArray()
        )
    }

    override fun getMainTypes(manufacturerCode: String): Observable<List<String>> {
        return mainTypeDao.all(manufacturerCode)
            .map { localItems ->
                localItems.map { localMainType ->
                    localMainType.name
                }
            }
    }

    override fun updateMainTypes(manufacturerCode: String, items: List<String>) {
        mainTypeDao.updateAll(
            manufacturerCode,
            *items.map { name ->
                LocalMainType(name, manufacturerCode)
            }.toTypedArray()
        )
    }

    override fun getBuiltDates(manufacturerCode: String, mainType: String): Observable<List<String>> {
        return builtDateDao.all(manufacturerCode, mainType)
            .map { localItems ->
                localItems.map { localBuiltDate ->
                    localBuiltDate.year
                }
            }
    }

    override fun updateBuiltDates(manufacturerCode: String, mainType: String, items: List<String>) {
        try {
            builtDateDao.insert(
                *items.map { year ->
                    LocalBuiltDate(year, manufacturerCode, mainType)
                }.toTypedArray()
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}