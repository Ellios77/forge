package forge.ai;

import forge.util.FileUtil;
import forge.util.TextUtil;

import java.io.File;
import java.util.*;

/**
 * Holds static desire multipliers for various actions.
 */
public class AiDesireProfile {
    public enum Action { PLAY_MANA_SOURCE, ATTACK_WITH_BIG_CREATURE }

    private final Map<Action, Double> multipliers = new EnumMap<>(Action.class);

    private static String PROFILE_DIR;
    private static final String EXT = ".desire";
    private static final Map<String, AiDesireProfile> LOADED = new HashMap<>();

    public double getMultiplier(Action a) {
        return multipliers.getOrDefault(a, 1.0);
    }

    private static String buildFileName(String name) {
        return TextUtil.concatNoSpace(PROFILE_DIR, File.separator, name, EXT);
    }

    private static AiDesireProfile loadProfile(String name) {
        AiDesireProfile prof = new AiDesireProfile();
        List<String> lines = FileUtil.readFile(buildFileName(name));
        for (String line : lines) {
            if (line.startsWith("#") || line.trim().isEmpty()) {
                continue;
            }
            String[] sp = line.split("=");
            if (sp.length == 2) {
                try {
                    Action a = Action.valueOf(sp[0].trim());
                    double v = Double.parseDouble(sp[1].trim());
                    prof.multipliers.put(a, v);
                } catch (Exception ignored) {
                }
            }
        }
        return prof;
    }

    public static void loadAllProfiles(String dir) {
        PROFILE_DIR = dir;
        LOADED.clear();
        File d = new File(dir);
        String[] children = d.list();
        if (children == null) { return; }
        for (String c : children) {
            if (c.endsWith(EXT)) {
                String name = c.substring(0, c.length() - EXT.length());
                LOADED.put(name, loadProfile(name));
            }
        }
    }

    public static AiDesireProfile getProfile(String name) {
        return LOADED.get(name);
    }
}
