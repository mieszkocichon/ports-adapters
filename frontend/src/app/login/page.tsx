"use client"

import { useState } from "react";
import axios from "axios"
import { useRouter } from "next/navigation";

const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const router = useRouter();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await axios.post(`${process.env.NEXT_PUBLIC_BRIDGE_API_URL}/api/auth/login`, {
                username,
                password
            });

            localStorage.setItem("token", response?.data?.token);

            router.push("dashboard");
        }
        catch(err) {
            setError("Invalid credentials, please try again.")
        }
    }

    return (
        <div className="flex items-center justify-center min-h-screen bg-grray-100">
            <div className="bg-white p-8 rounded-lg shadow-lg w-full sm:w-96">
                <h1 className="text-2xl font-bold text-center mb-6">Login</h1>

                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <input type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} 
                            className="w-full p-3 border border-gray-300 rounded-md focus:outlone-none focus:ring-2 focus:ring-indigo-500" />
                    </div>
                    <div className="mb-6">
                        <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)}
                            className="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500" />
                    </div>
                    {error && <p className="text-red-500 text-sm mb-4">{error}</p>}
                    <button type="submit" className="w-full py-3 bg-indigo-600 text-white rounded-md hover:bg-indigo-700 transition duration-300">
                        Login
                    </button>
                </form>
            </div>
        </div>
    )
}

export default Login;