<#import "template.ftl" as layout>
<@layout.registrationLayout displayInfo=social.displayInfo; section>
    <#if section = "title">
        ${msg("loginTitle",(realm.displayName!''))}
    <#elseif section = "header">
	<div class="title">
            ${msg("loginTitleHtml",(realm.displayNameHtml!''))}
        </div>
    <#elseif section = "form">
        <#if realm.password>
           <div class="auth">
            <div class="auth-container">
                <div class="card">
                    <header class="auth-header">
                    <a href="${properties.kcLogoLink!'#'}">
                        <h1 class="auth-title">
                            <div class="logo" id="kc-logo"> 

                            <span class="l l1"></span> 
                            <span class="l l2"></span>
                            <span class="l l3"></span> 
                            <span class="l l4"></span> 
                            <span class="l l5"></span>                                
                            </div>ModularAdmin </h1>
                            </a>
                    </header>
                    <div class="auth-content" id="">
                        <p class="text-xs-center">LOGIN TO CONTINUE</p>
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
                        <form id="login-form"  action="${url.loginAction}" method="post">
                            <div class="form-group"> 
                                <label for="username">Username</label> 
                                <#if usernameEditDisabled??>
                                    <input id="username" 
                                    class="form-control underlined" name="username" 
                                    value="${(login.username!'')?html}"
                                    placeholder="Your email address or username should be here"
                                    type="text" disabled />
                                <#else>
                                    <input id="username" class="form-control underlined" 
                                    name="username" value="${(login.username!'')?html}" 
                                     placeholder="Your email address or username"
                                    type="text" autofocus autocomplete="off" />
                                </#if>
                            </div>
                            <div class="form-group"> 
                                <label for="password">${msg("password")}</label> 
                                <input type="password"
                                    class="form-control underlined" name="password" id="password"
                                    placeholder="Your password" required autocomplete="off"> 
                            </div>
                            <div class="form-group"> 
                                <#if realm.rememberMe && !usernameEditDisabled??>
                                    <label for="rememberMe">
                                        <#if login.rememberMe??>
                                            <input id="rememberMe" name="rememberMe" type="checkbox" tabindex="3" checked /> 
                                            <span>${msg("rememberMe")}</span>
                                        <#else>
                                            <input id="rememberMe" name="rememberMe" type="checkbox" tabindex="3">
                                            <span>${msg("rememberMe")}</span>
                                        </#if>
                                    </label> 
                                 </#if>
                                <#if realm.resetPasswordAllowed>
                                    <span class="forgot-btn pull-right">
                                        <a href="${url.loginResetCredentialsUrl}">${msg("doForgotPassword")}</a>
                                    </span>
                                </#if>                            
                            </div>
                            <div class="form-group"> 
                                <button type="submit" class="btn btn-block btn-primary">Login</button>                            
                            </div>
                            <div class="form-group">
                                 <#if realm.password && realm.registrationAllowed && !usernameEditDisabled??>
                                    <p class="text-muted text-xs-center">
                                        <span>${msg("noAccount")} <a href="${url.registrationUrl}">${msg("doRegister")}</a></span>
                                    </p>
                                </#if>

                                <#if realm.password && social.providers??>
                                    <div id="kc-social-providers">
                                        <ul>
                                            <#list social.providers as p>
                                                <li><a href="${p.loginUrl}" id="zocial-${p.alias}" class="zocial ${p.providerId}"> <span class="text">${p.displayName}</span></a></li>
                                            </#list>
                                        </ul>
                                    </div>
                                </#if>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        </#if>        
    </#if>
</@layout.registrationLayout>