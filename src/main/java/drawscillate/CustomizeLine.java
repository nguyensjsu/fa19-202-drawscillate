package drawscillate;

public class CustomizeLine {

    private char key;

    private IColorInvoker redCol;
    private IColorInvoker yellowCol;
    private IColorInvoker orangeCol;
    private IColorInvoker blueCol;
    private IColorInvoker purpleCol;
    private IColorInvoker greenCol;
    private IColorInvoker blackCol;

    public CustomizeLine() {

        redCol = new ColorOption();
        yellowCol = new ColorOption();
        orangeCol = new ColorOption();
        blueCol = new ColorOption();
        purpleCol = new ColorOption();
        greenCol = new ColorOption();
        blackCol = new ColorOption();

    }

    public void setKey(char key) {
        this.key = key;
    }

    public char getKey() {
        return key;
    }

    public void initialize() {
        switch (key) {
        case 'r':
            redCol.invoke();
            break;
        case 'y':
            yellowCol.invoke();
            break;
        case 'b':
            blueCol.invoke();
            break;
        case 'g':
            greenCol.invoke();
            break;
        case 'p':
            purpleCol.invoke();
            break;
        case 'o':
            orangeCol.invoke();
            break;
        case ' ':
            blackCol.invoke();
            break;
        }
    }

    public void setColorItem(char key, IColorCommand color) {

        switch (key) {
        case ('r'):
            redCol.setCommand(color);
            break;
        case ('y'):
            yellowCol.setCommand(color);
            break;
        case ('b'):
            blueCol.setCommand(color);
            break;
        case ('g'):
            greenCol.setCommand(color);
            break;
        case ('p'):
            purpleCol.setCommand(color);
            break;
        case ('o'):
            orangeCol.setCommand(color);
            break;
        case (' '):
            blackCol.setCommand(color);
            break;

        }
    }
}
