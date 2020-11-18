package ge.edu.btu.server;

import ge.edu.btu.common.Partie;

import java.util.List;
import java.util.Map;

public interface ElectionService {

    void initParties(List<Partie> parties);

    List<Partie> getAllParties();

    void vote(int number);

    Map<Integer, Long> getResult();
}
