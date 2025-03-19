const twitter = (() => {

    let api = apiClient;

    const createPost = async () => {
        let content = document.getElementById('postContent').value;
        let username = localStorage.getItem("username");
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

    const updateCharacterCount = () => {
        let textarea = document.getElementById('postContent');
        let counter_characters = document.querySelector('.counter_characters');
        counter_characters.innerText = textarea.value.length;
    };

    return {
        createPost,
        getPostList,
        updateCharacterCount
    }

})();
twitter.getPostList();
