package de.dasphiller.ffa.kits

import de.dasphiller.ffa.kits.impl.BlazeKit
import de.dasphiller.kitapi.kit.addKit
import de.dasphiller.ffa.kits.impl.PhantomKit
import de.dasphiller.ffa.kits.impl.NinjaKit
import de.dasphiller.ffa.kits.impl.SoupKit

object KitManager {

    fun addKits() {
        addKit(PhantomKit())
        addKit(Revive())
        addKit(NinjaKit())
        addKit(SoupKit())
        addKit(GapMaster())
        addKit(DoubleTruple())
        addKit(BlazeKit())
    }

}