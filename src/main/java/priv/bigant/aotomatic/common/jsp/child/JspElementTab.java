package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.common.MyStringBuffer;
import priv.bigant.aotomatic.common.SpringElParse;
import priv.bigant.aotomatic.common.jsp.JspElement;

import java.util.Map;

public class JspElementTab extends JspElement {
    public JspElementTab(SpringElParse a, String b, String c, boolean d, Map<String, String> e) {
        super((String)null, (String)null);
        /*this.elementCode = "tab";
        a = this.appendParamsForUrl(a, e);
        a = (String)a.parseElExpress(a);
        MyStringBuffer myStringBuffer = MyStringBuffer.newInstance();
        JspElementTab var10000;
        String[] var10001;
        boolean var10003;
        if (a) {
            var10001 = new String[3];
            var10003 = true;
            var10001[0] = VisitToAutoCode.j("\u0004MQ\u0001[MYRK\u001c\u001aMYXMH\u0015UPHK\u0003\u0006");
            var10001[1] = a;
            var10001[2] = UpdateCodeFor.ALLATORIxDEMO("m}=;o");
            a.append(var10001);
            var10000 = a;
        } else {
            var10001 = new String[5];
            var10003 = true;
            var10001[0] = VisitToAutoCode.j("\u0004MQ\u001f\u0004@\u0018IJD^\u001c\u001a");
            var10001[1] = a;
            var10001[2] = UpdateCodeFor.ALLATORIxDEMO("po");
            var10001[3] = a;
            var10001[4] = VisitToAutoCode.j("\u0004\u000eY\u001f\u0004\u000eTH\u0006");
            a.append(var10001);
            var10000 = a;
        }

        var10000.html = a.toString();*/
    }

    public void genCode() {
    }

    public void initDefaultAttribute() {
    }

    public static JspElementTab newInstance(SpringElParse a, String b, String c, boolean d, Map<String, String> e) {
        return new JspElementTab(a, b, c, d, e);
    }
}
