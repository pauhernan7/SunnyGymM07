package com.example.sunnygym

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.fragment.app.Fragment

class Toolbar : Fragment() {
    // Variables de instancia
    private var mParam1: String? = null
    private var mParam2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_toolbar, container, false)

        // Verifica si el botón de menú existe antes de asignar el listener
        val menuButton = view.findViewById<ImageView>(R.id.menuButton)
        menuButton?.setOnClickListener { view: View -> this.showPopupMenu(view) }

        return view
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.profile_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem -> this.onMenuItemClick(item) }
        popupMenu.show()
    }

    private fun onMenuItemClick(item: MenuItem): Boolean {
        val itemId = item.itemId

        if (context == null) return false

        if (itemId == R.id.home) {
            startActivity(Intent(context, MyProfileActivity::class.java))
            return true
        } else if (itemId == R.id.my_profile) {
            startActivity(Intent(context, MyProfileActivity::class.java))
            return true
        } else if (itemId == R.id.guided_activities) {
            startActivity(Intent(context, GuidedActivitiesActivity::class.java))
            return true
        } else if (itemId == R.id.view_clubs) {
            startActivity(Intent(context, ViewClubsActivity::class.java))
            return true
        } else if (itemId == R.id.support) {
            startActivity(Intent(context, SupportActivity::class.java))
            return true
        } else if (itemId == R.id.lista_activities) {
            startActivity(Intent(context, ReservasActivty::class.java))
            return true
        } else if (itemId == R.id.logout) {
            startActivity(Intent(context, MainActivity::class.java))
            requireActivity().finish()
            return true
        } else {
            return false
        }
    }

    companion object {
        // Parámetros de argumentos
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        // Método de fábrica para crear una nueva instancia del fragmento
        fun newInstance(param1: String?, param2: String?): Toolbar {
            val fragment = Toolbar()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}