<article id="settings" class="row">
    <ul class="nav nav-pills col-2" id="list">
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#user-settings">Account Settings</a>
        </li>
        <li>
            <a class="nav-link" data-toggle="tab" href="#new-action">New Action</a>
        </li>
    </ul>

    <div class="tab-content col">
        <article class="tab-pane fade d-hidden" id="user-settings">
            <%@include file="account.jsp"%>
        </article>
        <article class="tab-pane fade d-hidden" id="new-action">
            <%@include file="actionBuilder.jsp"%>
        </article>
    </div>
</article>