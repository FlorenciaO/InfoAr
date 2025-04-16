<<<<<<<< HEAD:app/src/main/java/com/educacionit/infoar/presentacion/fragments/TermsAndConditionsFragment.kt
package com.educacionit.infoar.presentacion.fragments
========
package com.educacionit.infoar.presentation.fragments
>>>>>>>> refs/heads/feature/apply-orm-lite-resolution:app/src/main/java/com/educacionit/infoar/presentation/fragments/TermsAndConditionsFragment.kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.educacionit.infoar.R


class TermsAndConditionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_terms_and_conditions, container, false)
    }
}