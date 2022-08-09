package odravison.letscodechallenge.moviesbattle.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MapperUtils {

    /**
     * Model mapper.
     */
    protected static final ModelMapper MODEL_MAPPER = createModelMapper();

    /**
     * Private Constructor.
     */
    private MapperUtils() {
    }

    /**
     * Creates a model mapper instance.
     *
     * @return Model mapper instance.
     */
    static ModelMapper createModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }

    /**
     * Maps the source to destination class.
     *
     * @param source    Source.
     * @param destClass Destination class.
     * @return Instance of destination class.
     */
    public static <S, D> D mapTo(S source, Class<D> destClass) {
        return MODEL_MAPPER.map(source, destClass);
    }

    /**
     * Converts the source list to a destination list mapping the source items
     * to destination type items.
     *
     * @param items     Source items.
     * @param destClass Destination class.
     * @return List of instances of destination type.
     */
    public static <D, S> List<D> toList(List<S> items, Class<D> destClass) {
        List<D> dests = new ArrayList<>();

        if (items != null) {
            items.forEach(item -> dests.add(mapTo(item, destClass)));
        }
        return dests;
    }

    /**
     * Converts the source Set to a destination Set mapping the source items
     * to destination type items.
     *
     * @param items     Source items.
     * @param destClass Destination class.
     * @return Set of instances of destination type.
     */
    public static <D, S> Set<D> toSet(Set<S> items, Class<D> destClass) {
        Set<D> dests = new HashSet<>();

        if (items != null) {
            items.forEach(item -> dests.add(mapTo(item, destClass)));
        }
        return dests;
    }

}
