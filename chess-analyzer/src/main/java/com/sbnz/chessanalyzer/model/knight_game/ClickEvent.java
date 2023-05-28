package com.sbnz.chessanalyzer.model.knight_game;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("10m")
public abstract class ClickEvent {

}
