/*
 * This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in thCut even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.catrainbow.nocheatplus.checks.blockbreak

import cn.nukkit.event.Event
import cn.nukkit.event.block.BlockBreakEvent
import cn.nukkit.event.player.PlayerInteractEvent
import net.catrainbow.nocheatplus.NoCheatPlus
import net.catrainbow.nocheatplus.checks.CheckListener
import net.catrainbow.nocheatplus.checks.CheckType
import net.catrainbow.nocheatplus.feature.wrapper.WrapperPacketEvent

/**
 * 子监听器
 *
 * @author Catrainbow
 */
class BlockBreakListener : CheckListener(CheckType.BLOCK_BREAK) {

    override fun onTick(event: Event) {
        if (event is WrapperPacketEvent) {
            val player = event.player
            val data = NoCheatPlus.instance.getPlayerProvider(player)
            data.blockBreakData.onUpdate()
        } else if (event is BlockBreakEvent) {
            val player = event.player
            val data = NoCheatPlus.instance.getPlayerProvider(player)
            data.blockBreakData.onBreak(event)
        } else if (event is PlayerInteractEvent) {
            val player = event.player
            val data = NoCheatPlus.instance.getPlayerProvider(player)
            val action = event.action
            if (action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
                data.blockBreakData.onTicked()
            }
        }
    }

    init {
        this.addCheck(FastBreak())
    }

}