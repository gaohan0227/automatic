package priv.bigant.aotomatic.common;

import org.springframework.expression.AccessException;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.ast.PropertyOrFieldReference;

import java.util.Iterator;
import java.util.LinkedHashMap;

class J extends LinkedHashMap<String, Object> {
    LinkedHashMap<String, ExpressionState> K;
    private NamedScopeEvaluationContext namedScopeEvaluationContext;

    public Object get(Object a) {
        if (a == null) {
            return null;
        } else if (super.containsKey(a)) {
            return this.ALLATORIxDEMO(1);
        } else if (this.containsKey(a)) {
            ExpressionState var2 = this.ALLATORIxDEMO(a);
            return this.ALLATORIxDEMO((new PropertyOrFieldReference(true, a.toString(), 1)).getValue(var2));
        } else {
            return null;
        }
    }

    public boolean containsKey(Object a) {
        if (a == null) {
            return false;
        } else if (super.containsKey(a)) {
            return true;
        } else {
            return this.ALLATORIxDEMO(a) != null;
        }
    }

    /*protected Object ALLATORIxDEMO(Object a) {
        Object var2;
        for (Object var10000 = var2 = a; var10000 instanceof ExpressionState; var10000 = var2 = ((ExpressionState) var2).getRootContextObject().getValue()) {
        }

        return var2;
    }*/

    J(NamedScopeEvaluationContext var1) {
        this.namedScopeEvaluationContext = var1;
        this.K = new LinkedHashMap();
    }

    public Object ALLATORIxDEMO(String a, Object b) {
        ExpressionState expressionState = new ExpressionState(this.namedScopeEvaluationContext, new TypedValue(b), new SpelParserConfiguration(false, false));
        super.put(a, expressionState);
        return this.ALLATORIxDEMO(expressionState);
    }

    protected ExpressionState ALLATORIxDEMO(Object a) {
        if (this.K.containsKey(a)) {
            return this.K.get(a);
        } else {
            Iterator var3;
            for (Iterator var10000 = var3 = super.keySet().iterator(); var10000.hasNext(); var10000 = var3) {
                String var2 = (String) var3.next();

                Iterator var5;
                ExpressionState var11;
                for (var10000 = var5 = (var11 = (ExpressionState) super.get(var2)).getPropertyAccessors().iterator(); var10000.hasNext(); var10000 = var5) {
                    PropertyAccessor var4 = (PropertyAccessor) var5.next();

                    try {
                        boolean var12;
                        if (var4.getSpecificTargetClasses() != null && var4.getSpecificTargetClasses().length >= 1) {
                            label67:
                            {
                                Class[] var9;
                                int var8 = (var9 = var4.getSpecificTargetClasses()).length;

                                int var7;
                                for (int var13 = var7 = 0; var13 < var8; var13 = var7) {
                                    if (var9[var7].isAssignableFrom(var11.getRootContextObject().getValue().getClass())) {
                                        var12 = true;
                                        break label67;
                                    }

                                    ++var7;
                                }

                                var12 = false;
                            }
                        } else {
                            var12 = true;
                        }

                        if (var12 && var4.canRead(var11.getEvaluationContext(), var11.getRootContextObject().getValue(), a.toString())) {
                            this.K.put(a.toString(), var11);
                            return var11;
                        }
                    } catch (AccessException var10) {
                    }
                }
            }

            return null;
        }
    }
}
