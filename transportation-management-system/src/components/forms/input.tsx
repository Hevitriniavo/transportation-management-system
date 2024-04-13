    import { forwardRef } from "react";

    interface InputProps {
        type ?: string;
        placeholder ?: string;
        className ?: string;
        name: string;
        autoFocus ?: boolean;
        ref?: React.Ref<HTMLInputElement>; 
        onBlur ?: () => void; 
    }


    const Input = forwardRef<HTMLInputElement, InputProps>((props, ref) => {
        const { type = "text", placeholder = "", name,  className = "", autoFocus = false, onBlur} = props;
        return (
            <input
                className={className}
                type={type}
                placeholder={placeholder}
                name={name}
                autoFocus={autoFocus}
                onBlur={onBlur}
                id={name}
                ref={ref}
            />
        );

    })

    export default Input;