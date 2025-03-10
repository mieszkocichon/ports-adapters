"use client"

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";

const Dashboard = () => {
    const [userEntity, setUser] = useState(null);
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
            {userEntity && <p>Welcome, {userEntity?.username}!</p>}
        </div>
    )
}

export default Dashboard;