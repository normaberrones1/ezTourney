package com.techelevator.dao;


import com.techelevator.model.AcceptRejectTeamDto;
import com.techelevator.model.TeamDto;
import com.techelevator.model.UserDto;

import java.security.Principal;
import java.util.List;

public interface MemberDao {

    UserDto getMemberById(int userId);

    List<UserDto> getMembersByTeamId(int teamId);

    //as a user I want the teams I am part of
    List<TeamDto> getMyTeams(Principal principal);

    int getUserIdByName(String username);

    boolean isCurrentUserTeamCaptain(Principal principal, int teamId);

    void acceptRejectRequest(AcceptRejectTeamDto acceptReject);
}
