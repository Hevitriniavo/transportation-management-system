import { authenticate } from "@/app/lib/actions";

export default function Login() {
  return (
    <form className="flex flex-col items-center justify-center mt-10" action={authenticate}>
      <input
        type="email"
        name="email"
        placeholder="Email"
        required
        className="px-4 py-2 mb-4 border rounded-lg focus:outline-none focus:ring focus:border-blue-300"
      />
      <input
        type="password"
        name="password"
        placeholder="Password"
        required
        className="px-4 py-2 mb-4 border rounded-lg focus:outline-none focus:ring focus:border-blue-300"
      />
      <button
        type="submit"
        className="px-4 py-2 text-white bg-blue-500 rounded-lg hover:bg-blue-600 focus:outline-none focus:ring focus:border-blue-300"
      >
        Login
      </button>
    </form>
  );
}
