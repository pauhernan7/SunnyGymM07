package com.example.sunnygym.Register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistreViewModel: ViewModel() {

    private var _nomUsuari:String=""
    private var _email :String=""
    private var _contrassenya :String=""

    private val _formularivalid=MutableLiveData<Boolean>(false)
    val  formularivalid:MutableLiveData<Boolean> = _formularivalid

    private val _errorNomUsuari=MutableLiveData<String>("")
    val errorNomUsuari: LiveData<String> = _errorNomUsuari

    private val _errorEmail=MutableLiveData<String>("")
    val errorEmail: LiveData<String> = _errorEmail

    private val _errorContrassenya=MutableLiveData<String>("")
    val errorContrassenya: LiveData<String> = _errorContrassenya



    fun actualitzanomUsuari(nomusuari: String) {
        _nomUsuari = nomusuari
    }



    fun comprova_nomusuari() {

        if (_nomUsuari.isEmpty()) {
            _errorNomUsuari.value = "El nom d'usuari és obligatori"
        }else{
            _errorNomUsuari.value = ""
        }
    }
    fun  comprova_email(){
        if (_email.isEmpty()) {
            _errorEmail.value = "El email és obligatori"
        }else{
            _errorEmail.value = ""
        }
    }
    fun comprova_contrassenya(){
        if (_contrassenya.isEmpty()){
        _errorContrassenya  .value =
        }
    }

    fun actualitzaemail(email:String){
        _email=email
    }


    //Comprovació genérica. Comprova tots els camps.
    fun comprovadadesusuari() {
        comprova_nomusuari()
        comprova_email()
        comprova_contrassenya()
    }


    fun registrarusuari() {
        comprovadadesusuari()
        if (_formularivalid.value!!) {
            TODO("Cridar api retrofit per registrar usuari")
        }

    }

}