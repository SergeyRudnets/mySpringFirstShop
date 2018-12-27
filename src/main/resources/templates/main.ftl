<#import "parts/common.ftl" as c>

<@c.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="title" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by description">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>
</div>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add new Message
</a>
<div class="collapse <#if message??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="title" class="form-control ${(textError??)?string('is-invalid','')}"
                       value="<#if message??>${message.title}</#if>" name="title" placeholder="Введите сообщение" />
            <#if textError??>
                <div class="invalid-feedback">
                   ${textError}
                </div>
            </#if>
            </div>
            <div class="form-group">
                <input type="title" class="form-control"
                       value="<#if message??>${message.description}</#if>" name="description" placeholder="Тэг">
            <#if tagError??>
                <div class="invalid-feedback">
                    ${tagtError}
                </div>
            </#if>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>
</div>

<div class="card-columns">
    <#list messages as message>
    <div class="card my-3">
        <#if message.filename??>
        <img src="/img/${message.filename}" class="card-img-top">
        </#if>
        <div class="m-2">
            <span>${message.title}</span>
            <i>${message.description}</i>
        </div>
        <div class="card-footer title-muted">
            ${message.authorName}
        </div>
    </div>
    <#else>
    No message
    </#list>
</div>
</@c.page>
