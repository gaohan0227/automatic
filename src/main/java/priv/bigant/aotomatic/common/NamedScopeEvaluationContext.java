package priv.bigant.aotomatic.common;


import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class NamedScopeEvaluationContext extends StandardEvaluationContext {
    J ALLATORIxDEMO;

    public NamedScopeEvaluationContext() {
        this.ALLATORIxDEMO = new J(this);
        setRootObject(this.ALLATORIxDEMO);
        addPropertyAccessor(new MapAccessor());
    }

    public void addContext(String a, Object b) {
        this.ALLATORIxDEMO.ALLATORIxDEMO(a, b);
    }

    public <T> Object findContextObjectByName(String a) {
        return this.ALLATORIxDEMO.get(a);
    }
}
