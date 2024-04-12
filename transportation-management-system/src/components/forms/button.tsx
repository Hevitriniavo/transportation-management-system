import React from "react";

interface ButtonProps {
    text: React.ReactNode;
    type?: "button" | "reset" | "submit";
    onClick?: () => void;
    className?: string;
}

const Button: React.FC<ButtonProps> = ({ text, className = "", type = "button", onClick }) => {
    return (
        <button
            type={type}
            className={className}
            onClick={onClick}>
            {text}
        </button>
    );
}

export default Button;
