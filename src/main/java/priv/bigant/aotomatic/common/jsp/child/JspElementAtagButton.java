package priv.bigant.aotomatic.common.jsp.child;

import java.util.Map;

public class JspElementAtagButton extends JspElementAtag {
/*    public static final String operationType_view = "2";
    public static final String openDialogType_view = "2";
    public static final String operationType_edit = "0";
    public static final String openDialogType_edit_customBtn = "1";
    public static final String operationType_delete = "1";
    public static final String openDialogType_edit = "0";*/

    public JspElementAtagButton(boolean a, String b, String c, String d, String e, String f, Map<String, String> g) {
        super(a, b, c, d, e, g);
        elementCode = "atagButton";
        //ALLATORIxDEMO(a, a, a, a, a, a, a);
    }

    public static JspElementAtagButton newInstance(boolean a, String b, String c, String d, String e, String f, Map<String, String> g) {
        return new JspElementAtagButton(a, b, c, d, e, f, g);
    }

    public void genCode() {
        super.genCode();
    }

    public void addConfirmx(String a) {
        attribute.put("onclick", createAttrSimple("onclick", "return confirmx('" + a + "', this.href)"));
    }

    public enum ButtonType {

        OPERATION_TYPE_VIEW("2"),
        OPEN_DIALOG_TYPE_VIEW("2"),
        OPERATION_TYPE_EDIT("0"),
        OPEN_DIALOG_TYPE_EDIT_CUSTOMBTN("1"),
        OPERATION_TYPE_DELETE("1"),
        OPEN_DIALOG_TYPE_EDIT("0");
        String val;

        ButtonType(String val) {
            this.val = val;
        }

        public String getVal() {
            return val;
        }
    }
}
