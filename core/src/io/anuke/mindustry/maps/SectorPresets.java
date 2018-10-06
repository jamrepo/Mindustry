package io.anuke.mindustry.maps;

import com.badlogic.gdx.utils.Array;
import io.anuke.mindustry.content.Items;
import io.anuke.mindustry.content.Mechs;
import io.anuke.mindustry.content.blocks.UnitBlocks;
import io.anuke.mindustry.content.blocks.UpgradeBlocks;
import io.anuke.mindustry.entities.units.UnitCommand;
import io.anuke.mindustry.maps.missions.*;
import io.anuke.mindustry.type.Item;
import io.anuke.ucore.util.GridMap;
import io.anuke.ucore.util.Structs;

import static io.anuke.mindustry.Vars.mobile;

public class SectorPresets{
    private final GridMap<SectorPreset> presets = new GridMap<SectorPreset>(){{
        //base tutorial mission
        put(0, 0, new SectorPreset(TutorialSector.getMissions(), 1));

        //water mission
        put(-2, 0, new SectorPreset(Array.with(), 1));
        //command center mission
        //TODO generate enemy base
        //TODO make 2x2
        //TODO more gen info
        put(0, 1, new SectorPreset(Structs.array(new BlockMission(UnitBlocks.daggerFactory), Missions.blockRecipe(UnitBlocks.commandCenter),
            new CommandMission(UnitCommand.retreat), new CommandMission(UnitCommand.attack), new BattleMission()), 2));
        //reconstructor mission
        put(0, -1, new SectorPreset(Structs.array(Missions.blockRecipe(mobile ? UpgradeBlocks.tridentPad : UpgradeBlocks.deltaPad),
            new MechMission(Mechs.delta)), 1));
        //oil mission
        put(1, 0, new SectorPreset(Array.with(), 1));
    }};

    private final GridMap<Array<Item>> orePresets = new GridMap<Array<Item>>(){{
        put(0, 0, Array.with(Items.copper));
        put(1, 0, Array.with(Items.copper, Items.lead, Items.coal));
    }};

    public Array<Item> getOres(int x, int y){
        return orePresets.get(x, y);
    }

    public SectorPreset get(int x, int y){
        return presets.get(x, y);
    }

    public static class SectorPreset{
        public final Array<Mission> missions;
        public final int size;

        public SectorPreset(Array<Mission> missions, int size){
            this.missions = missions;
            this.size = size;
        }

        void generate(Sector sector){

        }
    }
}
