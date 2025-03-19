const twitter = (() => {

    let api = apiClient;

    const createPost = async () => {
        let content = document.getElementById('postContent').value;
        let username = "user";
        let post = JSON.stringify({ content, username });
        try {
            let response = await api.createPost(post)
            getPostList();
            return response;
        } catch (error) {
            console.error(error);
            alert(error);

        }
    };

    const getPostList = async () => {
        try {
            let response = await api.getPostList();
            let postList = document.getElementById("postList");
            postList.innerHTML = "";
            response.forEach(post => {
                postList.appendChild(createPostDiv(post));
            });
            return response;
        } catch (error) {
            console.error(error);
            alert(error);
        }
    }

    const createPostDiv = (post) => {
        const postItem = document.createElement("div");
        postItem.classList.add("post-item");
        postItem.innerHTML = `
            <p><strong>Username:</strong> ${post.username}</p>
            <p><strong>Content:</strong> ${post.content}</p>`
        return postItem;
    };

    return {
        createPost,
        getPostList
    }

})();
twitter.getPostList();