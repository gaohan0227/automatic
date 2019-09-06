
package priv.bigant.aotomatic.common;

public abstract class BaseController {
    /*@Value("${frontPath}")
    protected String frontPath;
    @Value("${urlSuffix}")
    protected String urlSuffix;
    @Autowired
    protected Validator validator;
    @Value("${adminPath}")
    protected String adminPath;
    protected Logger logger;*/

    /*protected void genInterfaceData(Class a, String a, List<String> a) {
        if (StringUtils.isNotBlank(a)) {
            (new AppIntefaceDataGen(a, a)).genJsonTestDataInclude(a);
        } else {
            (new AppIntefaceDataGen(a)).genJsonTestDataInclude(a);
        }
    }

    public static <T> T serviceByService(Class<T> a) {
        return SpringContextHolder.getBean(a);
    }

    protected void genInterfaceData(Class a) {
        (new AppIntefaceDataGen(a)).genJsonTestDataInclude((List)null);
    }

    @RequestMapping({"commonRejectForm"})
    public String commonRejectForm(HttpServletRequest a, HttpServletResponse a2, Model a) throws Exception {
        HttpServletResponse a = a.getParameter(VisitToAutoCode.j("S]K]BLs]@KNVgQDTEv@UDKRK"));
        String var4 = a.getParameter(ConditionBean.ALLATORIxDEMO("`sxsqb@sse}xAwdsGd~"));
        String var5 = a.getParameter(VisitToAutoCode.j("O]D\\uJ@VqYSYLKoYL]"));
        if (StringUtils.isEmpty(a)) {
            throw new Exception(ConditionBean.ALLATORIxDEMO("扬丛刢驥囌厉囲冓宫皒孊傾孅殣吟秦３"));
        } else {
            BaseEntity var6 = a.ALLATORIxDEMO(a);
            if (StringUtils.isNotEmpty(var5)) {
                String[] var10;
                int var12 = (var10 = var5.split(",")).length;

                int var9;
                for(int var10000 = var9 = 0; var10000 < var12; var10000 = var9) {
                    String var8 = var10[var9];
                    String var10002 = a.getParameter(var8);
                    boolean[] var10003 = new boolean[0];
                    boolean var10005 = true;
                    ++var9;
                    var4 = StringUtils.appendUrlParam(var4, var8, var10002, var10003);
                }
            }

            boolean var17;
            if (!var6.hasProperty(a)) {
                Object[] var10001 = new Object[1];
                var17 = true;
                var10001[0] = a;
                var6 = (BaseEntity)var6.initDynaMap(var10001);
            }

            Class var15 = var6.getClass();
            boolean[] var16 = new boolean[1];
            var17 = true;
            var16[0] = false;
            String var7 = JspElement.getEntityNameByClass(var15, var16);
            a.addAttribute(var7, var6);
            DynamicJspFormConfigVO var13 = DynamicJspFormConfigVO.newInstance(var6, VisitToAutoCode.j("宀栀驒囦"), var4);
            var13.setControllerSavePath(var13.getControllerBasePath() + var4);
            var13.modifyFieldTitle(a, ConditionBean.ALLATORIxDEMO("驡囈厍囶("));
            JspElementFormTextarea var14;
            (var14 = var13.addFormTextarea(a)).addValidateByClass("required");
            var13.dealAddMustInputFont(var14.getCorrespondJspElement());
            a.addAttribute(VisitToAutoCode.j("EAOYLQBrRHgWSUbWO^H_ww"), var13);
            return ConditionBean.ALLATORIxDEMO("\u007fyvc~sa9vo|w\u007f\u007fq9vo|w\u007f\u007fqP}d\u007f");
        }
    }

    protected boolean validateNoRepeate(RedirectAttributes a, BaseEntity a) {
        boolean var3 = true;
        if (StringUtils.isNotEmpty(a.getNoRepeateMessage())) {
            String[] var10002 = new String[1];
            boolean var10004 = true;
            var10002[0] = a.getNoRepeateMessage();
            a.addMessage(a, var10002);
            var3 = false;
        }

        return var3;
    }

    protected void addMessage(Model a, String... a) {
        StringBuilder var3 = new StringBuilder();
        String[] var7 = a;
        int var6 = a.length;

        int var5;
        for(int var10000 = var5 = 0; var10000 < var6; var10000 = var5) {
            String var4 = var7[var5];
            var3.append(var4).append(a.length > 1 ? VisitToAutoCode.j("\u0004CJ\u000e\u0006") : "");
            ++var5;
        }

        a.addAttribute(ConditionBean.ALLATORIxDEMO("\u007fsaesqw"), var3.toString());
    }

    @InitBinder
    protected void initBinder(WebDataBinder a) {
        a.registerCustomEditor(String.class, new K(a));
        a.registerCustomEditor(Date.class, new j(a));
    }

    public JoinTableBean findJTB(BaseEntity a, String a, String a, String a, boolean a) {
        JoinTableBean var6 = null;

        try {
            var6 = a.ALLATORIxDEMO().findJTB(a, a, a, a, a);
            return var6;
        } catch (Exception var7) {
            var7.printStackTrace();
            return var6;
        }
    }

    @ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})
    public String bindException(Exception a) throws Exception {
        if (MapperModifyPlugin.showPrintSql) {
            throw a;
        } else {
            return VisitToAutoCode.j("]SJNJ\u000e\f\u0011\b");
        }
    }

    public JoinTableBean findJoinTableBeanS(String a, String a, String a, String a, String... a) {
        JoinTableBean var6 = new JoinTableBean();
        if (StringUtils.isNotEmpty(a)) {
            var6.setMainTableName(a);
        }

        var6.setTableName(a);
        var6.putOnCond(a, a);
        if (a != null) {
            String a = a;
            String a = a.length;

            int a;
            for(int var10000 = a = 0; var10000 < a; var10000 = a) {
                a = a[a];
                ++a;
                var6.putSelect(a);
            }
        }

        return var6;
    }

    protected void addMessage(RedirectAttributes a, String... a) {
        StringBuilder var3 = new StringBuilder();
        String[] var7 = a;
        int var6 = a.length;

        int var5;
        for(int var10000 = var5 = 0; var10000 < var6; var10000 = var5) {
            String var4 = var7[var5];
            var3.append(var4).append(a.length > 1 ? ConditionBean.ALLATORIxDEMO(".t`9,") : "");
            ++var5;
        }

        a.addFlashAttribute(VisitToAutoCode.j("UDKRYF]"), var3.toString());
    }

    public String interfaceParamsValid(Object a, Class<?>... a) {
        try {
            BeanValidators.validateWithException(a.validator, a, a);
            return null;
        } catch (ConstraintViolationException var4) {
            List var10000 = BeanValidators.extractPropertyAndMessageAsList(var4, ConditionBean.ALLATORIxDEMO(",2"));
            String[] var10001 = new String[0];
            boolean var10003 = true;
            Object a = (String[])var10000.toArray(var10001);
            Class[] a = "";

            int var3;
            for(int var7 = var3 = 0; var7 < a.length; var7 = var3) {
                StringBuilder var8 = new StringBuilder(String.valueOf(a));
                String var9 = a[var3];
                ++var3;
                a = var8.append(var9).toString();
            }

            return a;
        }
    }

    public JoinTableBean findJoinTableBean(String a, String a, String a, String a, Map<String, String> a) {
        JoinTableBean var6 = new JoinTableBean();
        if (StringUtils.isNotEmpty(a)) {
            var6.setMainTableName(a);
        }

        var6.setTableName(a);
        var6.putOnCond(a, a);
        Iterator a;
        if (a != null) {
            for(Iterator var10000 = a = a.entrySet().iterator(); var10000.hasNext(); var10000 = a) {
                String a = (Map.Entry)a.next();
                var6.putSelect((String)a.getKey(), a.getValue() == null ? (String)a.getKey() : (String)a.getValue());
            }
        }

        return var6;
    }

    public List convertImageList(BaseEntity a, String a) {
        ArrayList var3 = new ArrayList();
        if (a != null && !StringUtils.isEmpty(a)) {
            String a;
            if (StringUtils.isEmpty(a = (String)Reflections.invokeGetter(a, a))) {
                return var3;
            } else {
                String[] var5;
                int var4 = (var5 = a.split(",")).length;

                int a;
                for(int var10000 = a = 0; var10000 < var4; var10000 = a) {
                    if (StringUtils.isNotEmpty(a = var5[a])) {
                        var3.add(a);
                    }

                    ++a;
                }

                return var3;
            }
        } else {
            return var3;
        }
    }

    public BaseEntity convertImageToBean(BaseEntity a, String a) {
        a.convertImageList(a, a);
        Object[] var10000 = new Object[2];
        boolean var10002 = true;
        boolean var10005 = false;
        var10000[0] = a + "List";
        var10000[1] = List.class;
        String a = var10000;
        Object[] var10001 = new Object[1];
        boolean var10003 = true;
        var10001[0] = a;
        return (BaseEntity)a.initDynaMap(var10001);
    }

    protected <T> T filterParseMultiUp(T a, HttpServletRequest a) {
        MultiValueMap a;
        if (a instanceof TjStandardMultipartHttpServletRequest && (a = ((TjStandardMultipartHttpServletRequest)a).getMultiFileMap()) != null && a.size() > 0 && a instanceof BaseEntity) {
            HashMap var3 = Maps.newHashMap();
            Iterator var5;
            Iterator var10000 = var5 = a.keySet().iterator();

            while(true) {
                while(var10000.hasNext()) {
                    Object var4 = var5.next();
                    List var6;
                    if ((var6 = (List)a.get(var4)) != null && var6 instanceof List && var6.size() > 1) {
                        var10000 = var5;
                        var3.put(var4.toString(), List.class);
                    } else {
                        var3.put(var4.toString(), MultipartFile.class);
                        var10000 = var5;
                    }
                }

                BaseEntity var11;
                a = (var11 = (BaseEntity)a).initDynaMap(var3);
                var10000 = var5 = a.entrySet().iterator();

                while(true) {
                    while(true) {
                        if (!var10000.hasNext()) {
                            return a;
                        }

                        Map.Entry var12 = (Map.Entry)var5.next();
                        HttpServletRequest a = var12.getKey().toString();
                        List var10 = (List)var12.getValue();

                        try {
                            if (var10 == null || !(var10 instanceof List) || var10.size() <= 1) {
                                BeanUtils.setProperty(a, a, var10.get(0));
                                break;
                            }

                            var10000 = var5;
                            BeanUtils.setProperty(a, a, var10);
                        } catch (InvocationTargetException | IllegalAccessException var7) {
                            var10000 = var5;
                            var7.printStackTrace();
                        }
                    }

                    var10000 = var5;
                }
            }
        } else {
            return a;
        }
    }

    protected String renderString(HttpServletResponse a, Object a) {
        return a.renderString(a, JsonMapper.toJsonString(a));
    }

    public static String ALLATORIxDEMO(String a) {
        int var10000 = (3 ^ 5) << 4 ^ 2 << 2 ^ 3;
        int var10001 = 4 << 3 ^ 2 ^ 5;
        int var10002 = 4 << 4 ^ 1 << 1;
        int var10003 = a.length();
        char[] var10004 = new char[var10003];
        boolean var10006 = true;
        int var5 = var10003 - 1;
        var10003 = var10002;
        int var3;
        var10002 = var3 = var5;
        char[] var1 = var10004;
        int var4 = var10003;
        var10000 = var10002;

        for(int var2 = var10001; var10000 >= 0; var10000 = var3) {
            var10001 = var3;
            char var6 = a.charAt(var3);
            --var3;
            var1[var10001] = (char)(var6 ^ var2);
            if (var3 < 0) {
                break;
            }

            var10002 = var3--;
            var1[var10002] = (char)(a.charAt(var10002) ^ var4);
        }

        return new String(var1);
    }

    @ModelAttribute
    public void get(Model a, HttpServletRequest a) {
        if (a instanceof TjStandardMultipartHttpServletRequest) {
            Iterator var4;
            for(Iterator var10000 = var4 = a.asMap().entrySet().iterator(); var10000.hasNext(); var10000 = var4) {
                Map.Entry var3;
                String var5 = (String)(var3 = (Map.Entry)var4.next()).getKey();
                if (var3.getValue() instanceof BaseEntity) {
                    Object var6 = a.filterParseMultiUp(var3.getValue(), a);
                    a.addAttribute(var5, var6);
                    return;
                }
            }

        }
    }

    @ExceptionHandler({AuthenticationException.class})
    public String authenticationException() {
        return VisitToAutoCode.j("]SJNJ\u000e\f\u0011\u000b");
    }

    public BaseController() {
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    protected Map parseObjectToMap(Object... a) {
        HashMap var2 = new HashMap();
        Object[] var5 = a;
        int var4 = a.length;

        int var3;
        for(int var10000 = var3 = 0; var10000 < var4; var10000 = var3) {
            Object a;
            if ((a = var5[var3]) instanceof Object[]) {
                Object[] var6 = (Object[])a;
                var2.put(var6[0], var6[1]);
            } else if (StringUtils.isNotEmpty("" + a)) {
                var2.put(a, a);
            }

            ++var3;
        }

        return var2;
    }

    protected boolean beanValidator(RedirectAttributes a, Object a, Class<?>... a) {
        try {
            BeanValidators.validateWithException(a.validator, a, a);
            return true;
        } catch (ConstraintViolationException var4) {
            Object a = BeanValidators.extractPropertyAndMessageAsList(var4, ConditionBean.ALLATORIxDEMO(",2"));
            a.add(0, VisitToAutoCode.j("效捏骴诠変贄Ｂ"));
            String[] var10003 = new String[0];
            boolean var10005 = true;
            a.addMessage(a, (String[])a.toArray(var10003));
            return false;
        }
    }

    public static CrudService service(Class a) {
        String var1 = null;

        String var10000;
        try {
            BaseEntity var4 = (BaseEntity)a.newInstance();
            boolean[] var10001 = new boolean[0];
            boolean var10003 = true;
            var1 = SpringContextHolder.findServiceNameByEntity(var4, var10001);
        } catch (IllegalAccessException | InstantiationException var3) {
            var10000 = var1;
            var3.printStackTrace();
            return (CrudService)SpringContextHolder.getBean(var10000);
        }

        var10000 = var1;
        return (CrudService)SpringContextHolder.getBean(var10000);
    }

    protected boolean ifExistRecord(CrudService a, DataEntity a, BaseEntity a) {
        boolean var4 = false;
        String[] var10002 = new String[0];
        boolean var10004 = true;
        CrudService a = a.findList(a, var10002);
        if (a != null) {
            MyBeanUtils.removeEntityFromListById(a, a);
        }

        if (a.size() > 0) {
            var4 = true;
        }

        return var4;
    }

    protected void beanValidator(Object a, Class<?>... a) {
        BeanValidators.validateWithException(a.validator, a, a);
    }

    public JoinTableBean findJTB(BaseEntity a, String a, boolean... a) {
        JoinTableBean var4 = null;

        try {
            var4 = a.ALLATORIxDEMO().findJTB(a, a, a);
            return var4;
        } catch (Exception var5) {
            var5.printStackTrace();
            return var4;
        }
    }

    public static CrudService service(BaseEntity a) {
        boolean[] var10001 = new boolean[0];
        boolean var10003 = true;
        return (CrudService)SpringContextHolder.getBean(SpringContextHolder.findServiceNameByEntity(a, var10001));
    }

    public String getBodyJson(HttpServletRequest a) {
        try {
            HttpServletRequest a = new BufferedReader(new InputStreamReader(a.getInputStream(), "utf-8"));
            StringBuilder var2 = new StringBuilder("");

            String var3;
            for(BufferedReader var10000 = a; (var3 = var10000.readLine()) != null; var10000 = a) {
                var2.append(var3);
            }

            a.close();
            String var6 = var2.toString();
            return var6;
        } catch (IOException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    protected boolean beanValidator(Model a, Object a, Class<?>... a) {
        try {
            BeanValidators.validateWithException(a.validator, a, a);
            return true;
        } catch (ConstraintViolationException var4) {
            Object a = BeanValidators.extractPropertyAndMessageAsList(var4, ConditionBean.ALLATORIxDEMO(",2"));
            a.add(0, VisitToAutoCode.j("效捏骴诠変贄Ｂ"));
            String[] var10003 = new String[0];
            boolean var10005 = true;
            a.addMessage(a, (String[])a.toArray(var10003));
            return false;
        }
    }

    public JoinTableBean findJoinTableBeanS(String a, String a, String a, String... a) {
        return a.findJoinTableBeanS((String)null, a, a, a, a);
    }

    public JoinTableBean findJTB(JoinTableBean a, String a, String a, String a, boolean a) {
        JoinTableBean var6 = null;

        try {
            var6 = a.ALLATORIxDEMO().findJTB(a, a, a, a, a);
            return var6;
        } catch (Exception var7) {
            var7.printStackTrace();
            return var6;
        }
    }

    protected String renderString(HttpServletResponse a, String a) {
        try {
            a.reset();
            a.setContentType(ConditionBean.ALLATORIxDEMO("wbf~\u007fqwf\u007f}x=|ay|"));
            a.setCharacterEncoding("utf-8");
            a.getWriter().print(a);
            return null;
        } catch (IOException var3) {
            return null;
        }
    }

    public String ParamsValid(Object a, Class<?>... a) {
        try {
            BeanValidators.validateWithException(a.validator, a, a);
            return null;
        } catch (ConstraintViolationException var4) {
            List var10000 = BeanValidators.extractPropertyAndMessageAsList(var4, VisitToAutoCode.j("\u001b\u0018"));
            String[] var10001 = new String[0];
            boolean var10003 = true;
            Object a = (String[])var10000.toArray(var10001);
            Class[] a = "";

            int var3;
            for(int var7 = var3 = 0; var7 < a.length; var7 = var3) {
                StringBuilder var8 = new StringBuilder(String.valueOf(a));
                String var9 = a[var3];
                ++var3;
                a = var8.append(var9).toString();
            }

            return a;
        }
    }

    public JoinTableBean findJTB(JoinTableBean a, String a, boolean... a) {
        JoinTableBean var4 = null;

        try {
            var4 = a.ALLATORIxDEMO().findJTB(a, a, a);
            return var4;
        } catch (Exception var5) {
            var5.printStackTrace();
            return var4;
        }
    }

    public JoinTableBean findJoinTableBean(String a, String a, String a, Map<String, String> a) {
        return a.findJoinTableBean((String)null, a, a, a, a);
    }

    protected void out(String a, HttpServletResponse a) {
        a.setCharacterEncoding("utf-8");
        PrintWriter var3 = null;

        PrintWriter var15;
        label108: {
            try {
                label94: {
                    try {
                        (var3 = a.getWriter()).print(a);
                    } catch (Exception var13) {
                        a.logger.error(ConditionBean.ALLATORIxDEMO("吃顣靰辅凨敦捼凬锋"), var13);
                        break label94;
                    }

                    var15 = var3;
                    break label108;
                }
            } catch (Throwable var14) {
                Throwable var10000;
                if (var3 != null) {
                    label110: {
                        try {
                            var3.close();
                        } catch (Exception var11) {
                            break label110;
                        }

                        var10000 = var14;
                        throw var10000;
                    }
                }

                var10000 = var14;
                throw var10000;
            }

            if (var3 != null) {
                try {
                    var3.close();
                    return;
                } catch (Exception var10) {
                    return;
                }
            }

            return;
        }

        if (var15 != null) {
            try {
                var3.close();
                return;
            } catch (Exception var12) {
            }
        }

    }

    public static String filterEmoji(String a) {
        return StringUtils.isNotBlank(a) ? a.replaceAll(VisitToAutoCode.j("zdT\\\u0019\b\u0011dT\\B\b\u0011\u0015}MEZG^}ME^G^}ME\u0000\u0011\b\fdT\\G^Ge"), ConditionBean.ALLATORIxDEMO("8")) : a;
    }*/
}
