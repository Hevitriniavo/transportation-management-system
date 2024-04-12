import React, { forwardRef } from "react";

interface SelectedProps {
    className?: string;
    name: string;
    onChange?: (event: React.ChangeEvent<HTMLSelectElement>) => void;
    data: { [key: string]: string };
}

const Selected = forwardRef<HTMLSelectElement, SelectedProps>((props, ref) => {
    const { name, className = "", onChange, data } = props;

    return (
        <select
            id={name}
            name={name}
            className={className}
            onChange={onChange}
            ref={ref}
        >
            {Object.entries(data).map(([value, label]) => (
                <option key={value} value={value}>{label}</option>
            ))}
        </select>
    );
});

export default Selected;
