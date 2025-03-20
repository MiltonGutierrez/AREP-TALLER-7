const apiClient = (() => {
    const urlPost = "https://eraza9z54a.execute-api.us-east-1.amazonaws.com/dev/api";

    // GET Post

    const getPostList = async () => {
        const jwt = localStorage.getItem("token");
        const response = await fetch(urlPost + '/post', {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + `${jwt}`,
                "Content-Type": "application/json"
            }
        });
        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.error || "Invalid credentials");
        }
        return response.json();
    };

    // POST Users

    const createUser = (data) => {
        return fetch(url + "/user", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });
    }

    const authUser = (data) => {
        return fetch(url + "/auth", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        })
    }

    // POST

    const createPost = async (post) => {
        const jwt = localStorage.getItem("token");
        const response = await fetch(urlPost + '/post', {
            method: 'POST',
            headers: {
                "Authorization": "Bearer " + `${jwt}`,
                "Content-Type": "application/json"
            },
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
        createPost,
        createUser,
        authUser
    }

})();