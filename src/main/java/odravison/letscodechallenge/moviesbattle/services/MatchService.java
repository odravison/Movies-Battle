package odravison.letscodechallenge.moviesbattle.services;

import lombok.Getter;
import odravison.letscodechallenge.moviesbattle.config.dto.LoggedUserDTO;
import odravison.letscodechallenge.moviesbattle.dto.MatchInfoDTO;
import odravison.letscodechallenge.moviesbattle.entities.Match;
import odravison.letscodechallenge.moviesbattle.repositories.MatchRepository;
import odravison.letscodechallenge.moviesbattle.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static odravison.letscodechallenge.moviesbattle.utils.MessagesUtils.NO_USER_LOGGED_IN;
import static odravison.letscodechallenge.moviesbattle.utils.MessagesUtils.USER_HAS_MATCH_CREATED;

@Service
@Getter
public class MatchService extends BaseEntityService<Match, Long> {

    @Autowired
    private MatchRepository repository;

    @Autowired
    private UserService userService;


    public MatchInfoDTO createMatch() throws Exception {

        createMatchValidations();

        Match match = new Match(userService.getLoggedUser().getId());
        this.insert(match);

        MatchInfoDTO matchInfo = MapperUtils.mapTo(match, MatchInfoDTO.class);

        return matchInfo;
    }

    private void createMatchValidations() throws Exception {
        if (!userService.isUserLoggedIn()) {
            throw new Exception(NO_USER_LOGGED_IN);

        } else if (userHasMatchCreated()) {
            throw new Exception(USER_HAS_MATCH_CREATED);
        }
    }

    @Transactional(readOnly = true)
    public Boolean userHasMatchCreated() {
        LoggedUserDTO loggedUserDTO = userService.getLoggedUser();
        Boolean hasMatch = this.repository.existsMatchByUserIdAndMatchEndedFalse(loggedUserDTO.getId());
        return hasMatch == null ? Boolean.FALSE : hasMatch;
    }

    public void deleteAll() {
        this.repository.deleteAll();
    }
}
