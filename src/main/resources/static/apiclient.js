const apiClient = (() => {
    const url = "/api";

    // GET
    const getPostList = async () => {
        const response = await fetch(url + '/post');
        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.error || "Invalid credentials");
        }
        return response.json();
    };

    // POST

    const createPost = async (post) => {
        const response = await fetch(url + '/post', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: post
        });
        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.error || "Failed");
        }

        return response.json();
    };

    return {
        getPostList,
        createPost
    }

})();