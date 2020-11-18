package ge.edu.btu.server;

import ge.edu.btu.common.Partie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElectionServiceImpl implements ElectionService {

    private List<Partie> parties;

    private Map<Integer, Long> result = new HashMap<>();

    @Override
    public void initParties(List<Partie> parties) {
        this.parties = parties;
        for (Partie partie : parties) {
            result.put(partie.getNumber(), 0L);
        }
    }

    @Override
    public List<Partie> getAllParties() {
        return parties;
    }

    @Override
    public void vote(int number) {
        if (result.containsKey(number)) {
            result.put(number, result.get(number) + 1);
        }
    }

    @Override
    public Map<Integer, Long> getResult() {
        return result;
    }
}
