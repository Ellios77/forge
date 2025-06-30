package forge.ai;

import java.util.EnumMap;
import java.util.Map;

/**
 * Tracks accumulated desire scores for the AI.
 */
public class AiDesireState {
    private final AiDesireProfile profile;
    private final Map<AiDesireProfile.Action, Double> values = new EnumMap<>(AiDesireProfile.Action.class);

    public AiDesireState(AiDesireProfile profile) {
        this.profile = profile;
    }

    public void newTurn() {
        for (AiDesireProfile.Action a : AiDesireProfile.Action.values()) {
            double inc = profile.getMultiplier(a);
            values.merge(a, inc, Double::sum);
        }
    }

    public void consume(AiDesireProfile.Action a) {
        double inc = profile.getMultiplier(a);
        values.merge(a, -inc, Double::sum);
    }

    public double get(AiDesireProfile.Action a) {
        return values.getOrDefault(a, 0.0);
    }
}
