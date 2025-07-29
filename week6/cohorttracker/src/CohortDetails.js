import React from "react";
import './cohortDetails.css'; // Adjust path as needed

function CohortDetails(props) {
    const { cohort } = props;

    return (
        <div className="cohort-container">
            <h3 className="cohort-heading">
                {cohort.cohortCode} -
                <span>{cohort.technology}</span>
            </h3>
            <dl>
                <dt>Started On</dt>
                <dd>{cohort.startDate}</dd>
                <dt>Current Status</dt>
                <dd>{cohort.currentStatus}</dd>
                <dt>Coach</dt>
                <dd>{cohort.coachName}</dd>
                <dt>Trainer</dt>
                <dd>{cohort.trainerName}</dd>
            </dl>
        </div>
    );
}

export default CohortDetails;
