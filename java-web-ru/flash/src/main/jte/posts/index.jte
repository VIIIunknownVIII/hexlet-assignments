@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
content = @`
    <h1>Posts</h1>
    <a href="${NamedRoutes.buildPostPath()}">Add Post</a>
    @if(page.getFlash() != null)
        <p class="alert alert-success" role="alert">${page.getFlash()}</p>
    @endif
    @if(page.getPosts().isEmpty())
        <p>Still no posts</p>
    @else
        @for(var post : page.getPosts())
            <div>
                <h2><a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a></h2>
            </div>
        @endfor
    @endif
`
)