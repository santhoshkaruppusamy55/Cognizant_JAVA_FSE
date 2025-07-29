import React, { Component } from "react";
import Post from "./Posts";

class Posts extends Component {
    constructor(props) {
        super(props);
        this.state = {
            posts: [],
            hasError: false,
        };
    }

    loadPosts = async () => {
        try {
            const response = await fetch("https://jsonplaceholder.typicode.com/posts");
            const data = await response.json();
            const postObjects = data.map(
                post => new Post(post.userId, post.id, post.title, post.body)
            );
            this.setState({ posts: postObjects });
        } catch (error) {
            console.error("Failed to fetch posts:", error);
            this.setState({ hasError: true });
        }
    };

    componentDidMount() {
        this.loadPosts();
    }

    componentDidCatch(error, info) {
        alert("An error occurred in Posts component.");
        console.error("Caught an error:", error, info);
        this.setState({ hasError: true });
    }

    render() {
        if (this.state.hasError) {
            return <h2>Something went wrong while loading posts.</h2>;
        }

        return (
            <div>
                <h2>Blog Posts</h2>
                {this.state.posts.map(post => (
                    <div key={post.id} style={{ marginBottom: "20px" }}>
                        <h3>{post.title}</h3>
                        <p>{post.body}</p>
                    </div>
                ))}
            </div>
        );
    }
}

export default Posts;
