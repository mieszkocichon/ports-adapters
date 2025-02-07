"use client"

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";

const Dashboard = () => {
    const [user, setUser] = useState(null);
    const router = useRouter();

    useEffect(() => {
        const token = localStorage.getItem("token");
        if (!token) {
            router.push("login");
        }
        else {
            setUser({ username: "John Tom" })
        }
    }, []);

    return (
        <div>
            <h1>Dashboard</h1>
            {user && <p>Welcome, {user?.username}!</p>}
        </div>
    )
}

export default Dashboard;