<#import "template.ftl" as layout>
<@layout.registrationLayout; section>
    <#if section = "title">
        ${msg("registerWithTitle",(realm.displayName!''))}
    <#elseif section = "header">
        ${msg("registerWithTitleHtml",(realm.displayNameHtml!''))}
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
                        <p class="text-xs-center">SIGNUP TO GET INSTANT ACCESS</p>
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
                        <form id="signup-form" action="${url.registrationAction}" method="post">
                            <div class="form-group"> 
                            <label for="firstname">Name</label>
                                <div class="row ${messagesPerField.printIfExists('firstName',properties.kcFormGroupErrorClass!)} 
                                ${messagesPerField.printIfExists('lastName',properties.kcFormGroupErrorClass!)}">
                                    <div class="col-sm-6"> 
                                        <input type="text" class="form-control underlined" 
                                        name="firstName" id="firstName" placeholder="${msg('firstName')}" 
                             
                                        value="${(register.formData.firstName!'')?html}" autofocus=""> 
                                    </div>
                                    <div class="col-sm-6"> 
                                        <input type="text" class="form-control underlined" 
                                        name="lastName" id="lastName" placeholder="${msg('lastName')}" 
                                     value="${(register.formData.lastName!'')?html}"> 
                                    </div>
                                </div>
                            </div>
                            <div class="form-group ${messagesPerField.printIfExists('email',properties.kcFormGroupErrorClass!)}"> 
                                <label for="email">${msg("email")}</label> 
                                <input type="email" class="form-control underlined" 
                                    name="email" id="email" 
                                    placeholder="Enter email address" 
                                    value="${(register.formData.email!'')?html}"> 
                            </div>
                            <#if passwordRequired>
                                <div class="form-group ${messagesPerField.printIfExists('password',properties.kcFormGroupErrorClass!)}"> <label for="password">Password</label>
                                    <div class="row">
                                        <div class="col-sm-6"> 
                                            <input type="password" 
                                            class="form-control underlined" name="password" id="password" 
                                            placeholder="${msg('password')}"> 
                                        </div>
                                        <div class="col-sm-6"> 
                                            <input type="password" 
                                            class="form-control underlined" 
                                            name="password-confirm" id="password-confirm" placeholder="${msg('passwordConfirm')}" required="">
                                         </div>
                                    </div>
                                </div>
                            </#if>
                           <!-- <div class="form-group"> 
                                <label for="agree">
                                    <input class="checkbox" name="agree" id="agree" type="checkbox" required=""> 
                                    <span>Agree the terms and <a href="#">policy</a></span>
                                </label> 
                            </div>
                            -->
                            <#if recaptchaRequired??>
                                <div class="form-group">
                                    <div class="${properties.kcInputWrapperClass!}">
                                        <div class="g-recaptcha" data-size="compact" data-sitekey="${recaptchaSiteKey}"></div>
                                    </div>
                                </div>
                            </#if>
                            <div class="form-group"> 
                                <button type="submit" class="btn btn-block btn-primary">Sign Up</button> 
                            </div>
                            <div class="form-group">
                                <p class="text-muted text-xs-center">${msg("alreadyRegistered")}  
                                <a href="${url.loginUrl}">${msg("doLogIn")}</a></p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </#if>
</@layout.registrationLayout>