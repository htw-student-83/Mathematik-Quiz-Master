package com.example.programtointerfacetest.control

//import com.example.programtointerfacetest.network.wifidirect.WirelessConnectionFacade
//import com.example.programtointerfacetest.network.wifidirect.WirelessConnectionFacadeImpl
import android.content.Context
import com.example.model.accounts.AccountCreationResult
import com.example.model.accounts.TopFiveUser
import com.example.model.accounts.provideAccountFacade
import com.example.model.mathematics.provideExerciseFacade
import com.example.programtointerfacetest.model.ModelServiceFacade
import com.example.programtointerfacetest.model.ModelServiceFacadeImpl
import com.example.model.accounts.User
import com.example.programtointerfacetest.model.database.DatabaseServiceFacadeImpl
import com.example.programtointerfacetest.network.protocolengine.ConnectionType
import com.example.programtointerfacetest.network.protocolengine.ProtocolEngineForWireless


class ControlerSingletonImpl private constructor(context: Context): Controler {

    private lateinit var model: ModelServiceFacade

    //TODO the controler only knows the protocolengine doesn't know the network?

    private val protocolEngineWireless = ProtocolEngineForWireless()

    private val minLengthPassword = 5
    private val maxLengthPassword = 20
    val currentPoints = 0

    //Beim erzeugen einer Instanz der Klasse Controler wird der init-Block ausgeführt
    init {
        val database = DatabaseServiceFacadeImpl.getInstance(context)
        database?.let{
            val account = provideAccountFacade(it)
            val mathematics = provideExerciseFacade(it)
            model = ModelServiceFacadeImpl(account, mathematics)
        }
    }

    companion object{
        private var instance: Controler? = null
        fun getInstance(context: Context): Controler?{
            if(instance == null){
                instance = ControlerSingletonImpl(context)
                return instance
            }
            return instance
        }
    }

    override fun checkUerDataForRegistration2(firstname: String, lastname: String,
        password: String, points: Int, logged: Boolean, activeSince: String
    ): AccountCreationResult {
        return when{
            firstname.isEmpty() &&  lastname.isEmpty() && password.isEmpty()
                -> AccountCreationResult.Error("All fields must be filled.")
            firstname.isEmpty() -> AccountCreationResult.Error("Your firstname is missing")
            lastname.isEmpty() -> AccountCreationResult.Error("Your lastname is missing")
            password.isEmpty() -> AccountCreationResult.Error("Your password is missing")
            !firstname.first().isUpperCase() -> AccountCreationResult.Error("Your firstname is invalid")
            !lastname.first().isUpperCase() -> AccountCreationResult.Error("Your lastname is invalid")
            password.length < minLengthPassword -> AccountCreationResult.Error("Your password is too short")
            password.length >= maxLengthPassword -> AccountCreationResult.Error("Your password is too long")
                else -> {
                val result = this.model.createAccount2(firstname, lastname, password, 0,
                    false, activeSince)
                    result
            }
        }
    }

    override fun checkUserPassword(password: String): String {
        return when {
            password.isEmpty() -> "Your password is missing"
            password.length < minLengthPassword -> "Your password is too short"
            password.length >= maxLengthPassword -> "Your password is too long"

            else -> {
                when (
                    val result = this.model.checkPassword(password)
                ) {
                    "password not found" -> "Please start registration."
                    "one element found"-> ""
                    else -> result
                }
            }
        }
    }

    override fun userLogout(userID: Long): Boolean {
        return this.model.logout(userID)
    }

    override fun userAccountDelete(userID: Long): Boolean {
        return this.model.deleteAccount(userID)
    }

    override fun userDataReload(): User? {
        return this.model.reloadData()
    }

    override fun startNewGameRound() {
        this.model.startTimer()
    }

    override fun stopCurrentRound() {
        this.model.stopTimer()
    }

    override fun loadNewExercise(): String {
        return this.model.generateNewExercise()
    }

    override fun checkUserAnswer(answer: Int): Boolean {
        //TODO evaluate the syntax and than tranform to int
        return this.model.evaluateAnswer(answer)
    }

    override fun saveResultForTopFive(points: Int) {
        this.model.savePoints(points)
    }

    override fun incrementPoints(points: Int) {
        this.model.incrementPoints(points)
    }

    override fun sendGamePointsAboveOutputStream(points: Int, type: ConnectionType) {
        this.protocolEngineWireless.giveGamePointsToOutputStream(points, type)
    }

    override fun readGamePointsFromInputStream(): Int {
       return this.protocolEngineWireless.readGamePointsFromInputStream()
    }

    override fun loadCurrentPoints(): Int {
        return this.model.getCurrentPointsFromMathematics()
    }

    override fun reloadLoggedUserForARunningGame(): String {
        return this.model.getLoggedUsername()
    }

    override fun resetCurrentPoints(){
        return this.model.setInitialPoints()
    }

    override fun getTheTopFive(): List<TopFiveUser?> {
        return this.model.getDataOfBestFiveUser()
    }

    //TODO eventuell sogar überflüssig, weil die protocolEngine von Bluetooth gekannt wird.
    override fun startConnectionAboveBluetooth() {
       // return this.network.wirelessContactWithBluetooth()
    }

    override fun startConnectionAboveWifiDirect() {
       // return this.network.wirelessContactWithWifiDirect()
    }


}