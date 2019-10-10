package ai.tomorrow.tokenjar.utilities

import ai.tomorrow.tokenjar.data.WalletDatabase
import ai.tomorrow.tokenjar.data.WalletRepository
import ai.tomorrow.tokenjar.managewallets.ManageWalletsViewModelFactory
import android.content.Context

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

//    private fun getPlantRepository(context: Context): PlantRepository {
//        return PlantRepository.getInstance(
//            AppDatabase.getInstance(context.applicationContext).plantDao())
//    }
//
//    private fun getWalletRepository(context: Context): WalletRepository {
//        return WalletRepository.getInstance(
//            WalletDatabase.getInstance(context.applicationContext).walletDatabaseDao())
//    }
//
//    fun provideManageWalletViewModelFactory(
//        context: Context
//    ): ManageWalletsViewModelFactory {
//        val repository = getGardenPlantingRepository(context)
//        return GardenPlantingListViewModelFactory(repository)
//    }

//    fun providePlantListViewModelFactory(context: Context): PlantListViewModelFactory {
//        val repository = getPlantRepository(context)
//        return PlantListViewModelFactory(repository)
//    }
//
//    fun providePlantDetailViewModelFactory(
//        context: Context,
//        plantId: String
//    ): PlantDetailViewModelFactory {
//        return PlantDetailViewModelFactory(getPlantRepository(context),
//            getGardenPlantingRepository(context), plantId)
//    }
}