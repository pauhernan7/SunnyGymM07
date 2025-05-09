package com.example.sunnygym.Register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistreViewModel : ViewModel() {

    private var _nomUsuari: String = ""
    private var _email: String = ""
    private var _contrassenya: String = ""
    private var _repetirContrassenya: String = ""

    private val _formularivalid = MutableLiveData(false)
    val formularivalid: LiveData<Boolean> = _formularivalid

    private val _errorNomUsuari = MutableLiveData("")
    val errorNomUsuari: LiveData<String> = _errorNomUsuari

    private val _errorEmail = MutableLiveData("")
    val errorEmail: LiveData<String> = _errorEmail

    private val _errorContrassenya = MutableLiveData("")
    val errorContrassenya: LiveData<String> = _errorContrassenya

    fun actualitzanomUsuari(nomusuari: String) {
        _nomUsuari = nomusuari
    }

    fun actualitzaemail(email: String) {
        _email = email
    }

    fun actualitzacontrassenya(contrasenya: String) {
        _contrassenya = contrasenya
    }

    fun actualitzarepetircontrasenya(repetir: String) {
        _repetirContrassenya = repetir
    }

    fun comprova_nomusuari() {
        _errorNomUsuari.value = when {
            _nomUsuari.isBlank() -> "El nom d'usuari és obligatori"
            _nomUsuari.length < 3 -> "El nom ha de tenir almenys 3 caràcters"
            _nomUsuari.length > 20 -> "El nom d’usuari és massa llarg"
            !_nomUsuari.matches(Regex("^[a-zA-Z0-9]+\$")) -> "Nom d’usuari no vàlid"
            _nomUsuari != _nomUsuari.trim() -> "El nom no pot tenir espais al principi o final"
            _nomUsuari.matches(Regex("^\\d+\$")) -> "El nom d’usuari ha de contenir lletres"
            else -> ""
        }
    }

    fun comprova_email() {
        _errorEmail.value = when {
            _email.isBlank() -> "El correu electrònic és obligatori"
            !_email.matches(Regex("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}\$")) -> "Format de correu electrònic no vàlid"
            _email.contains("<") || _email.contains(">") -> "Caràcter no permès en una direcció de correu electrònic"
            _email.any { it.isUpperCase() } -> "Caràcter no permès en una adreça de correu electrònic"
            _email == "seth.perez@gmail.com" -> "Aquest correu ja està registrat" // simulació per test
            else -> ""
        }
    }

    fun comprova_contrassenya() {
        _errorContrassenya.value = when {
            _contrassenya.isBlank() -> "La contrasenya és obligatòria"
            _repetirContrassenya.isBlank() -> "Heu de confirmar la contrasenya"
            _contrassenya != _repetirContrassenya -> "Les contrasenyes no coincideixen"
            _contrassenya.length < 6 -> "La contrasenya ha de tenir almenys 6 caràcters"
            _contrassenya.length > 20 -> "La contrasenya és massa llarga"
            !_contrassenya.any { it.isUpperCase() } -> "Ha d’incloure almenys una majúscula"
            !_contrassenya.any { it.isLowerCase() } -> "Ha d’incloure almenys una minúscula"
            !_contrassenya.any { it.isDigit() } -> "Ha d’incloure almenys un número"
            !_contrassenya.any { !it.isLetterOrDigit() } -> "Inclou un símbol"
            else -> ""
        }
    }

    fun comprovadadesusuari() {
        comprova_nomusuari()
        comprova_email()
        comprova_contrassenya()

        _formularivalid.value = _errorNomUsuari.value!!.isEmpty() &&
                _errorEmail.value!!.isEmpty() &&
                _errorContrassenya.value!!.isEmpty()
    }


}
