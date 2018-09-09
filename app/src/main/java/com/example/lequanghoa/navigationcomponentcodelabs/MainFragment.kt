package com.example.lequanghoa.navigationcomponentcodelabs

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController

/**
 * Fragment used to show how to navigate to another destination
 */
class MainFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //TODO STEP 5 - Set an OnClickListener, using Navigation.createNavigateOnClickListener()
//        view.findViewById<Button>(R.id.navigate_dest_bt)?.setOnClickListener (
//            Navigation.createNavigateOnClickListener(R.id.flow_step_one, null)
//            )
        //TODO ENDSTEP 5

        // TODO STEP 6 - Set NavOptions
        val options = NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right)
                .build()
        view.findViewById<Button>(R.id.navigate_dest_bt)?.setOnClickListener {
            findNavController(it).navigate(R.id.flow_step_one, null, options)
        }
        // TODO ENDSTEP 6

        // TODO STEP 7 - Update the OnclickListener to navigate using an action
        view.findViewById<Button>(R.id.navigate_action_bt)?.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.next_action, null)
        )

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main_menu, menu)
    }
}
