import Image from "next/image";
import Link from "next/link";

export default function Home() {
  return (
    <div>
      <h1>Home Page</h1>
      <ul>
        <li>
          <Link href="login">Login</Link>
        </li>
        <li>
          <Link href="dashboard">Dashboard</Link>
        </li>
      </ul>
    </div>
  );
}
