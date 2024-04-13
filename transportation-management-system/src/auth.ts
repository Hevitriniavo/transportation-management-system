import NextAuth from "next-auth"
import Credentials from "next-auth/providers/credentials"
import { postData } from "./services/postData";
import { jwtDecode } from "jwt-decode";
import { UserJwtData } from "./types/type";

export const { handlers, auth } = NextAuth({
    providers: [
        Credentials({
            name: "Credentials",
            credentials: {},
            authorize: async (credentials) => {
                const { email, password } = credentials as { email: string; password: string };
                try {
                    const token: { accessToken: string; } = await postData("/auth/login", { email, password });
                    const user: UserJwtData = jwtDecode(token?.accessToken);
                    return { email: user.sub, image: user.image, name: token.accessToken };
                } catch (error) {
                    console.error("Authorization error:", error);
                    return null;
                }
            },
        }),
    ],
    pages: {
        signIn: "/login"
    },
    secret: process.env.AUTH_SECRET
})