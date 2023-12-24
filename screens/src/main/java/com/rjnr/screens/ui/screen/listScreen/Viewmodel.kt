<<<<<<< HEAD:screens/src/main/java/com/rjnr/screens/ui/screen/list/Viewmodel.kt
package com.rjnr.screens.ui.screen.list
=======
package com.rjnr.screens.ui.screen.listScreen
>>>>>>> master:screens/src/main/java/com/rjnr/screens/ui/screen/listScreen/Viewmodel.kt

import androidx.lifecycle.ViewModel
import com.rjnr.navigation.ListScreen
import com.rjnr.navigation.Navigation

class ListViewModel(private val nav: Navigation = Navigation()) : ViewModel() {

    fun start(screen: ListScreen) {
        nav.onBackPressed[screen]
    }
}