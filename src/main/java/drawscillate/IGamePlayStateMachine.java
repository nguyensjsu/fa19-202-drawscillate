package drawscillate;

public interface IGamePlayStateMachine {
    void setStateInitial();
    
    void setStateInPlay();
    
    void setStateWin();
    
    void setStateLose();
}
