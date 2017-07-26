<#import "template.ftl" as layout>
<@layout.registrationLayout displayInfo=true; section>
    <#if section = "title">
        ${msg("updatePasswordTitle")}
    <#elseif section = "header">
        ${msg("updatePasswordTitle")}
    <#elseif section = "form">
        <!--<form id="kc-passwd-update-form" class="form update-password ${properties.kcFormClass!}" action="${url.loginAction}" method="post">
            <input type="text" readonly value="this is not a login form" style="display: none;">
            <input type="password" readonly value="this is not a login form" style="display: none;">

            <div class="update-password-field ${properties.kcFormGroupClass!}">
                <div class="${properties.kcLabelWrapperClass!}">
                    <label for="password-new" class="${properties.kcLabelClass!}">${msg("passwordNew")}</label>
                </div>
                <div class="${properties.kcInputWrapperClass!}">
                    <input type="password" id="password-new" name="password-new" class="form-control ${properties.kcInputClass!}" autofocus autocomplete="off" />
                </div>
            </div>

            <div class="update-password-field ${properties.kcFormGroupClass!}">
                <div class="${properties.kcLabelWrapperClass!}">
                    <label for="password-confirm" class="${properties.kcLabelClass!}">${msg("passwordConfirm")}</label>
                </div>
                <div class="${properties.kcInputWrapperClass!}">
                    <input type="password" id="password-confirm" name="password-confirm" class="form-control ${properties.kcInputClass!}" autocomplete="off" />
                </div>
            </div>

            <div class="${properties.kcFormGroupClass!} row update-password-button-container">
                <div id="kc-form-options" class="${properties.kcFormOptionsClass!} col-xs-6 col-sm-8">
                    <div class="${properties.kcFormOptionsWrapperClass!}">
                    </div>
                </div>

                <div id="kc-form-buttons" class="${properties.kcFormButtonsClass!} col-xs-6 col-sm-4">
                    <input class="btn btn-primary btn-flat btn-block ${properties.kcButtonClass!} ${properties.kcButtonPrimaryClass!} ${properties.kcButtonLargeClass!}" type="submit" value="${msg("doSubmit")}"/>
                </div>
            </div>
        </form>-->
         <div class="auth">
            <div class="auth-container">
                <div class="card">
                    <header class="auth-header">
                        <h1 class="auth-title">
                            <div class="logo"> 
                                <span class="l l1"></span> 
                                <span class="l l2"></span> 
                                <span class="l l3"></span> 
                                <span class="l l4"></span> 
                                <span class="l l5"></span> 
                            </div> ModularAdmin 
                        </h1>
                    </header>
                    <div class="auth-content">
                        <p class="text-xs-center">PASSWORD RECOVER</p>
                        <p class="text-muted text-xs-center">
                        <small>Reset Password</small></p>

                        <#if message?has_content>
                            <div class="${properties.kcFeedbackAreaClass!}">
                                <div class="alert alert-${message.type}">
                                    <#if message.type = 'success'><span class="${properties.kcFeedbackSuccessIcon!}"></span></#if>
                                    <#if message.type = 'warning'><span class="${properties.kcFeedbackWarningIcon!}"></span></#if>
                                    <#if message.type = 'error'><span class="${properties.kcFeedbackErrorIcon!}"></span></#if>
                                    <#if message.type = 'info'><span class="${properties.kcFeedbackInfoIcon!}"></span></#if>
                                    <span class="kc-feedback-text">${message.summary}</span>
                                </div>
                            </div>
                        </#if>
                        
                        <form id="reset-password-form" action="${url.loginAction}" method="post">
                            <input type="text" readonly value="this is not a login form" style="display: none;">
                            <input type="password" readonly value="this is not a login form" style="display: none;">

                            <div class="form-group"> 
                                <label for="password-new">${msg("passwordNew")}</label> 
                                <input type="password"
                                    class="form-control underlined" name="password-new" id="password-new"
                                    placeholder="Your password" required autofocus autocomplete="off"> 
                            </div>

                            <div class="form-group"> 
                                <label for="password-confirm">${msg("passwordConfirm")}</label> 
                                <input type="password"
                                    class="form-control underlined" name="password-confirm" id="password-confirm"
                                    placeholder="Your password" required autocomplete="off"> 
                            </div>

                             <div class="form-group"> 
                                <button type="submit" class="btn btn-block btn-primary" type="submit" value="${msg('doSubmit')}">${msg("doSubmit")}</button>                            
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </#if>
</@layout.registrationLayout>