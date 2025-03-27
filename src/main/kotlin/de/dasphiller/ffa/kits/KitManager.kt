package de.dasphiller.ffa.kits

import de.dasphiller.ffa.kits.impl.*
import de.dasphiller.kitapi.kit.addKit

object KitManager {

    fun addKits() {
        addKit(PhantomKit())
        addKit(Revive())
        addKit(NinjaKit())
        addKit(SoupKit())
        addKit(GapMaster())
        addKit(BoostKit())
        addKit(BlazeKit())
    }

}