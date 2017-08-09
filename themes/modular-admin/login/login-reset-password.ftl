<#import "template.ftl" as layout>
<@layout.registrationLayout displayInfo=true; section>
    <#if section = "title">
        ${msg("emailForgotTitle")}
    <#elseif section = "header">
        ${msg("emailForgotTitle")}
    <#elseif section = "form">
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
                        <small>Enter your email address to recover your password.</small></p>
                        <form id="reset-form" action="${url.loginAction}" method="post">
                            <div class="form-group"> 
                                <label for="username">
                                    <#if !realm.loginWithEmailAllowed>${msg("username")}
                                    <#elseif !realm.registrationEmailAsUsername>${msg("usernameOrEmail")}
                                    <#else>${msg("email")}
                                    </#if>
                                </label> 
                                <input type="text" class="form-control underlined" 
                                    id="username" name="username" placeholder="Your email address" 
                                    required="required" autofocus="autofocus"> 
                                </div>
                            <div class="form-group"> <button type="submit" class="btn btn-block btn-primary">Reset</button> </div>
                            <div class="form-group clearfix"> 
                                <a class="pull-left" href="${url.loginUrl}">
                                    ${msg("backToLogin")}
                                </a> 
                                <a class="pull-right" href="${url.registrationUrl}">
                                    ${msg("doRegister")}
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    <#elseif section = "info" >
        <hr />
        ${msg("emailInstruction")}
    </#if>
</@layout.registrationLayout>